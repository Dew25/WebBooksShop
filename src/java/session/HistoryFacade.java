/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Book;
import entity.History;
import entity.Reader;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jvm
 */
@Stateless
public class HistoryFacade extends AbstractFacade<History> {

    @PersistenceContext(unitName = "WebBooksShoplPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoryFacade() {
        super(History.class);
    }

    public List<History> findReadingBooks(Reader reader) {
        try {
            return em.createQuery("SELECT h FROM History h WHERE h.returnDate = NULL AND h.reader = :reader")
                    .setParameter("reader", reader)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Book> findPurchasedBook(Reader reader) {
        try {
            return em.createQuery("SELECT h.book FROM History h WHERE h.reader = :reader")
                    .setParameter("reader", reader)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
}
