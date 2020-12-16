package com.site.services;

import com.site.dao.PositionDao;
import com.site.entities.Position;

import java.util.List;

public class PositionService {
    private final PositionDao positionDao = new PositionDao();

    public Position getPosition(int id) {
        return positionDao.get(id);
    }

    public Position getPosition(String name) {
        return getPosition(getIdByName(name));
    }

    public void savePosition(Position position) {
        positionDao.save(position);
    }

    public void deletePosition(Position position) {
        positionDao.delete(position);
    }

    public void updatePosition(Position position) {
        positionDao.update(position);
    }

    public List<Position> getAllPositions() {
        return positionDao.getAll();
    }

    private int getIdByName(String name) {
        return positionDao.getIdByName(name);
    }

    public boolean contains(String name) {
        return getAllPositions().stream().anyMatch(o -> o.getName()
                                                         .toLowerCase()
                                                         .equals(name.toLowerCase()));
    }
}
