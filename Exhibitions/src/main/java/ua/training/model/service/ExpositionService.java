package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.impl.JDBCExhibitionHallDao;
import ua.training.model.dao.impl.JDBCExpositionDao;
import ua.training.model.dao.impl.JDBCTicketDao;
import ua.training.model.entity.ExhibitionHall;
import ua.training.model.entity.Exposition;
import ua.training.model.service.util.Utils;

import java.util.List;

public class ExpositionService {
    private int postOnPage = 3;
    private JDBCExpositionDao expoDao = DaoFactory.getInstance().createExpositionDao();
    private JDBCExhibitionHallDao hallDao = DaoFactory.getInstance().createExhibitionHallDao();


    public List<Exposition> getExpoList(String currentPage, String hallId){
        if(Utils.isNumber(currentPage)){
            int offset = Integer.parseInt(currentPage);
            offset = offset <= 0 ? 0 : (offset - 1) * postOnPage;

            if(Utils.isNumber(hallId)){
                return expoDao.getInRangeHall(offset, postOnPage, Integer.parseInt(hallId));
            }else {
                return expoDao.getInRange(offset, postOnPage);
            }
        } else {
            int offset = 0;
            if(Utils.isNumber(hallId)){
                return expoDao.getInRangeHall(offset, postOnPage, Integer.parseInt(hallId));
            } else {
                return expoDao.getInRange(offset, postOnPage);
            }
        }
    }

    public List<ExhibitionHall> getHalls(){
        return hallDao.getAll();
    }

    public int getNumberOfPages(String hallId) {
        int noOfPages;
        int noOfRows;
        if(Utils.isNumber(hallId)){
            int id = Integer.parseInt(hallId);
             noOfRows =  expoDao.getNumberRows(id);
        } else {
            noOfRows =  expoDao.getNumberRows();
        }
        noOfPages = noOfRows / postOnPage;
        if((noOfRows % postOnPage) != 0){
            noOfPages++;
        }
        return noOfPages;
    }

    public void add(String theme, String shortDesc, String fullDesc, String priceStr, String hallIdSrt){
        if(!Utils.isNotNull(theme,shortDesc)){
            return;
        }
        if(!Utils.isNumber(priceStr) || !Utils.isNumber(hallIdSrt)){
            return;
        }
        int price = Integer.parseInt(priceStr);
        if(price < 0) return;
        int hallId = Integer.parseInt(hallIdSrt);
        if(hallId < 0) return;
        Exposition expo = new Exposition();
        //todo builder?
        expo.setTheme(theme);
        expo.setPrice(price);
        expo.setShortDescription(shortDesc);
        expo.setFullDescription(fullDesc);
        ExhibitionHall hall = new ExhibitionHall();
        hall.setId(hallId);
        expo.setHall(hall);

        expoDao.insert(expo);
    }

    public void delete(String expoIdStr){
        if(!Utils.isNumber(expoIdStr)) return;
        int expoId = Integer.parseInt(expoIdStr);
        if(expoId < 0) return;
        Exposition expo = new Exposition();
        expo.setId(expoId);
        expoDao.delete(expo);
    }

    public void update(String expoIdStr, String theme, String shortDesc,
                       String fullDesc, String priceStr, String hallIdSrt){
        if(!Utils.isNotNull(theme,shortDesc)){
            return;
        }
        if(!Utils.isNumber(expoIdStr) || !Utils.isNumber(priceStr) || !Utils.isNumber(hallIdSrt)){
            return;
        }
        int price = Integer.parseInt(priceStr);
        if(price < 0) return;
        int expoId = Integer.parseInt(expoIdStr);
        if(expoId < 0) return;
        int hallId = Integer.parseInt(hallIdSrt);
        if(hallId < 0) return;
        Exposition expo = new Exposition();
        //todo builder?
        expo.setId(expoId);
        expo.setTheme(theme);
        expo.setPrice(price);
        expo.setShortDescription(shortDesc);
        expo.setFullDescription(fullDesc);
        ExhibitionHall hall = new ExhibitionHall();
        hall.setId(hallId);
        expo.setHall(hall);
        expoDao.update(expo);
    }

    public List<Exposition> getExpositions(){
        return expoDao.getAll();
    }

    public Exposition getExposition(String expoId) {
        System.out.println("-- str id " + expoId);
        if(Utils.isNumber(expoId)){
            System.out.println("It`s number");
            return expoDao.getById(Integer.parseInt(expoId));
        }
        return null;
    }
}
