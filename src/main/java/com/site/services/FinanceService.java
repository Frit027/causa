package com.site.services;

import com.site.dao.FinanceDao;
import com.site.entities.Finance;

import java.util.List;

public class FinanceService {
    private final FinanceDao financeDao = new FinanceDao();

    public void saveFinance(Finance finance) {
        financeDao.save(finance);
    }

    public void updateFinance(Finance finance) {
        financeDao.update(finance);
    }

    public List<Finance> getAll() {
        return financeDao.getAll();
    }
}
