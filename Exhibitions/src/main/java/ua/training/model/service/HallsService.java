package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.impl.JDBCExhibitionHallDao;
import ua.training.model.dao.impl.JDBCExpositionDao;
import ua.training.model.entity.ExhibitionHall;
import ua.training.model.service.util.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HallsService {

    private static volatile HallsService instance;
    private JDBCExhibitionHallDao hallDao = DaoFactory.getInstance().createExhibitionHallDao();
    private JDBCExpositionDao expoDao = DaoFactory.getInstance().createExpositionDao();
    private List<ExhibitionHall> halls;
    private Map<Integer, Integer> rowsByHall = new HashMap<>();

    private HallsService() {
        halls = hallDao.getAllOK();
        rowsByHall.put(-1, expoDao.getNumberRows());
    }

    public static HallsService getInstance() {
        if (instance == null) {
            synchronized (HallsService.class) {
                if (instance == null) {
                    instance = new HallsService();
                }
            }
        }
        return instance;
    }


    public void add(String name, String info) {
        if (name != null && info != null) {
            ExhibitionHall hall = new ExhibitionHall();
            hall.setName(name);
            hall.setInformation(info);
            hallDao.insert(hall);
            updateList(hall);
        }
    }

    public void delete(String idStr) {
        if (Utils.isNumber(idStr)) {
            int id = Integer.parseInt(idStr);
            if (id < 0) return;
            ExhibitionHall hall = new ExhibitionHall();
            hall.setId(id);
            hallDao.saveDelete(hall);
            updateList(hall);
            rowsByHall.remove(hall.getId());
        }
    }

    public void update(String idStr, String name, String info) {
        if (name != null && info != null && Utils.isNumber(idStr)) {
            ExhibitionHall hall = new ExhibitionHall.Builder()
                    .setId(Integer.parseInt(idStr))
                    .setName(name)
                    .setInformation(info)
                    .build();
            hallDao.update(hall);
            updateList(hall);
        }
    }

    public List<ExhibitionHall> getHalls() {
        return halls;
    }

    public int getNumberOfRows(int hallId) {
        if (rowsByHall.containsKey(hallId)) {
            return rowsByHall.get(hallId);
        }
        int rows = expoDao.getNumberRows(hallId);
        rowsByHall.put(hallId, rows);
        return rows;
    }

    public int getNumberOfRows() {
        return rowsByHall.get(-1);
    }

    void updateList(ExhibitionHall hall) {
        this.updateList(hall.getId());
    }

    void updateList(int hallId){
        halls = hallDao.getAllOK();
        rowsByHall.put(hallId, expoDao.getNumberRows(hallId));
        rowsByHall.put(-1, expoDao.getNumberRows());
    }
}
