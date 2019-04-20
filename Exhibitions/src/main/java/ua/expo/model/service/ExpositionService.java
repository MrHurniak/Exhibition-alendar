package ua.training.model.service;

import org.apache.log4j.Logger;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.impl.JDBCExpositionDao;
import ua.training.model.entity.ExhibitionHall;
import ua.training.model.entity.Exposition;
import ua.training.model.service.util.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;
import java.util.Properties;

public class ExpositionService {

    private static volatile ExpositionService instance;
    private static final String SETTINGS_PROP = "/settings.properties";
    private static final Logger LOGGER = Logger.getLogger(ExpositionService.class);

    private int postOnPage;
    private JDBCExpositionDao expoDao = DaoFactory.getInstance().createExpositionDao();
    private HallsService hallsService = HallsService.getInstance();

    private ExpositionService(){
        try(InputStream stream = ExpositionService.class.getResourceAsStream(SETTINGS_PROP)){
            Properties properties = new Properties();
            properties.load(stream);
            postOnPage = Integer.parseInt(properties.getProperty("post.on.page"));
            LOGGER.debug("Load count of post on page from property file. Value=" + postOnPage);
        } catch (IOException e){
            LOGGER.warn("Cannot load settings file or count value is invalid");
            postOnPage = 3;
        }
    }

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

    public void add(String theme, String shortDesc, String fullDesc, String priceStr,
                    String date, String date_to, String hallIdSrt) {
        int price = Integer.parseInt(priceStr);
        int hallId = Integer.parseInt(hallIdSrt);
        if (hallId < 0 || price < 0) {
            throw new IllegalArgumentException("Wrong value of price or wrong hallId!");
        }
        Date date_start = Date.valueOf(date);
        Date date_end = Date.valueOf(date_to);
        if(date_start.after(date_end)){
            throw new IllegalArgumentException("Wrong date order!");
        }
        Exposition.Builder builder = new Exposition.Builder();
        builder.setTheme(theme)
                .setShortDescription(shortDesc)
                .setFullDescription(fullDesc)
                .setPrice(price)
                .setDate(date_start)
                .setDateTo(date_end)
                .setHall(new ExhibitionHall.Builder().setId(hallId).build());
        expoDao.insert(builder.build());
        hallsService.updateList(hallId);
    }

    public void delete(String expoIdStr) {
        int expoId = Integer.parseInt(expoIdStr);
        if (expoId < 0) return;
        expoDao.saveDelete(new Exposition.Builder().setId(expoId).build());
        hallsService.updateList(expoDao.getById(expoId).getHall().getId());
    }
    
    public void update(String expoIdStr, String theme, String shortDesc,
                       String fullDesc, String priceStr, String date, String date_to, String hallIdSrt) {
        int price = Integer.parseInt(priceStr);
        int expoId = Integer.parseInt(expoIdStr);
        int hallId = Integer.parseInt(hallIdSrt);
        if (hallId < 0 || price < 0 || expoId < 0) {
            return;
        }
        Date date_start = Date.valueOf(date);
        Date date_end = Date.valueOf(date_to);
        if(date_start.after(date_end)){
            throw new IllegalArgumentException("Wrong date order!");
        }
        Exposition.Builder builder = new Exposition.Builder();
        builder.setId(expoId)
                .setDate(date_start)
                .setDateTo(date_end)
                .setTheme(theme)
                .setShortDescription(shortDesc)
                .setFullDescription(fullDesc)
                .setHall(new ExhibitionHall.Builder().setId(hallId).build())
                .setPrice(price);
        expoDao.update(builder.build());
    }

    public List<Exposition> getExpositions() {
        return expoDao.getAllNotDeleted();
    }

    public Exposition getExposition(String expoId) {
        if (Utils.isNumber(expoId)) {
            return expoDao.getById(Integer.parseInt(expoId));
        }
        return null;
    }
}
