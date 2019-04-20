package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.impl.JDBCExhibitionHallDao;
import ua.training.model.dao.impl.JDBCExpositionDao;
import ua.training.model.entity.ExhibitionHall;

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
        hallDao.insert(new ExhibitionHall.Builder()
                .setName(name)
                .setInformation(info).build());
        updateList();
    }

    public void delete(String idStr) {
        int id = Integer.parseInt(idStr);
        if (id < 0) return;
        hallDao.saveDelete(new ExhibitionHall.Builder().setId(id).build());
        updateList(id);
        rowsByHall.remove(id);
    }

    public void update(String idStr, String name, String info) {
        ExhibitionHall hall = new ExhibitionHall.Builder()
                .setId(Integer.parseInt(idStr))
                .setName(name)
                .setInformation(info)
                .build();
        hallDao.update(hall);
        updateList(hall.getId());
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

    void updateList() {
        halls = hallDao.getAllOK();
    }

    void updateList(int hallId) {
        this.updateList();
        rowsByHall.put(hallId, expoDao.getNumberRows(hallId));
        rowsByHall.put(-1, expoDao.getNumberRows());
    }
}
