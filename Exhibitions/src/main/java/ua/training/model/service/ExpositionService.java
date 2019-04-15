package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.impl.JDBCExpositionDao;
import ua.training.model.entity.ExhibitionHall;
import ua.training.model.entity.Exposition;
import ua.training.model.service.util.Utils;

import java.sql.Date;
import java.util.List;

public class ExpositionService {

    private static volatile ExpositionService instance;

    private int postOnPage = 3;
    private JDBCExpositionDao expoDao = DaoFactory.getInstance().createExpositionDao();
    private HallsService hallsService = HallsService.getInstance();


    private ExpositionService(){}

    public static ExpositionService getInstance(){
        if(instance == null){
            synchronized (ExpositionService.class){
                if(instance == null){
                    instance = new ExpositionService();
                }
            }
        }
        return instance;
    }


    public List<Exposition> getExpoList(String currentPage, String hallId) {
        if (Utils.isNumber(currentPage)) {
            int offset = Integer.parseInt(currentPage);
            offset = offset <= 0 ? 0 : (offset - 1) * postOnPage;

            if (Utils.isNumber(hallId)) {
                return expoDao.getInRangeHall(offset, postOnPage, Integer.parseInt(hallId));
            } else {
                return expoDao.getInRange(offset, postOnPage);
            }
        } else {
            int offset = 0;
            if (Utils.isNumber(hallId)) {
                return expoDao.getInRangeHall(offset, postOnPage, Integer.parseInt(hallId));
            } else {
                return expoDao.getInRange(offset, postOnPage);
            }
        }
    }


    public int getNumberOfPages(String hallId) {
        int noOfRows = Utils.isNumber(hallId)
                ? hallsService.getNumberOfRows(Integer.parseInt(hallId))
                : hallsService.getNumberOfRows();
        int noOfPages = noOfRows / postOnPage;
        if ((noOfRows % postOnPage) != 0) {
            noOfPages++;
        }
        return noOfPages;
    }

    public void add(String theme, String shortDesc, String fullDesc, String priceStr, String date, String date_to,
                    String hallIdSrt) {
        if (!Utils.isNotNull(theme, shortDesc)) {
            return;
        }
        if (!Utils.isNumber(priceStr) || !Utils.isNumber(hallIdSrt)) {
            return;
        }
        int price = Integer.parseInt(priceStr);
        int hallId = Integer.parseInt(hallIdSrt);
        if (hallId < 0 || price < 0) {
            return;
        }

        Exposition.Builder builder = new Exposition.Builder();
        builder.setTheme(theme)
                .setShortDescription(shortDesc)
                .setFullDescription(fullDesc)
                .setPrice(price)
                .setDate(Date.valueOf(date))
                .setDateTo(Date.valueOf(date_to))
                .setHall(new ExhibitionHall.Builder().setId(hallId).build());
        expoDao.insert(builder.build());
    }

    public void delete(String expoIdStr) {
        if (!Utils.isNumber(expoIdStr)) return;
        int expoId = Integer.parseInt(expoIdStr);
        if (expoId < 0) return;
        expoDao.saveDelete(new Exposition.Builder().setId(expoId).build());
    }

    public void deleteByHallId(int hallId){
        expoDao.saveDeleteByHallId(hallId);
    }

    public void update(String expoIdStr, String theme, String shortDesc,
                       String fullDesc, String priceStr, String date, String date_to, String hallIdSrt) {
        if (!Utils.isNotNull(theme, shortDesc, date)) {
            return;
        }
        if (!Utils.isNumber(expoIdStr) || !Utils.isNumber(priceStr) || !Utils.isNumber(hallIdSrt)) {
            return;
        }
        int price = Integer.parseInt(priceStr);
        int expoId = Integer.parseInt(expoIdStr);
        int hallId = Integer.parseInt(hallIdSrt);
        if (hallId < 0 || price < 0 || expoId < 0) {
            return;
        }
        Exposition.Builder builder = new Exposition.Builder();
        builder.setId(expoId)
                .setDate(Date.valueOf(date))
                .setDateTo(Date.valueOf(date_to))
                .setTheme(theme)
                .setShortDescription(shortDesc)
                .setFullDescription(fullDesc)
                .setHall(new ExhibitionHall.Builder().setId(hallId).build())
                .setPrice(price);
        expoDao.update(builder.build());
    }

    public List<Exposition> getExpositions() {
        return expoDao.getAll();
    }

    public Exposition getExposition(String expoId) {
        if (Utils.isNumber(expoId)) {
            return expoDao.getById(Integer.parseInt(expoId));
        }
        return null;
    }

    public int getPostOnPage() {
        return postOnPage;
    }

    public void setPostOnPage(int postOnPage) {
        this.postOnPage = postOnPage;
    }
}
