package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.impl.JDBCExhibitionHallDao;
import ua.training.model.entity.ExhibitionHall;
import ua.training.model.service.util.Utils;

import java.util.List;

public class HallsService {
    JDBCExhibitionHallDao hallDao = DaoFactory.getInstance().createExhibitionHallDao();

    public void add(String name, String info){
        if(name != null && info != null) {
            ExhibitionHall hall = new ExhibitionHall();
            hall.setName(name);
            hall.setInformation(info);
            hallDao.insert(hall);
        }
    }

    public void delete(String idStr){
        if(Utils.isNumber(idStr)){
            int id = Integer.parseInt(idStr);
            if(id < 0) return;
            ExhibitionHall hall = new ExhibitionHall();
            hall.setId(id);
            hallDao.delete(hall);
        }
    }

    public void update(String idStr, String name, String info){
        if(name != null && info != null && Utils.isNumber(idStr)){
            hallDao.update(new ExhibitionHall(Integer.parseInt(idStr), name, info));
        }
    }

    public List<ExhibitionHall> getHalls() {
        return hallDao.getAll();
    }
}
