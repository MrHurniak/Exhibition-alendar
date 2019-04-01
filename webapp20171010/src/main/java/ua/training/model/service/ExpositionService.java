package ua.training.model.service;

import ua.training.model.dao.impl.JDBCExpositionDao;
import ua.training.model.dao.impl.JDBCTicketDao;
import ua.training.model.entity.Exposition;

import java.util.List;

public class ExpositionService {
    private int postOnPage = 10;
    private JDBCTicketDao ticketDao;
    private JDBCExpositionDao expoDao;

    public int ticketsLeft(Exposition expo){
        return 0;
    }

    public List<Exposition> getNext(int currentPage){
        return expoDao.getInRange(currentPage * postOnPage, postOnPage);
    }

}
