package com.centroAcademico.SistemaAcademico.Service.Impl;

import com.centroAcademico.SistemaAcademico.Domain.Notas;
import com.centroAcademico.SistemaAcademico.Service.NotasService;
import com.centroAcademico.SistemaAcademico.Dao.NotasDao;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotasServiceImpl implements NotasService {

    @Autowired
    private NotasDao notasDao;

    @Override
    @Transactional(readOnly = true)
    public List<Notas> obtenerNotas() {
        return notasDao.obtenerNotas();
    }

    @Override
    @Transactional(readOnly = true)
    public Notas getNotaPorId(Notas nota) {
        List<Notas> notas = notasDao.getNotaPorId(nota.getIdNota());
        return notas.isEmpty() ? null : notas.get(0);
    }

    @Override
    @Transactional
    public void insertarNota(Notas notas) {
        notasDao.insertarNotas(
                notas.getProfesor().getIdProfesor(),
                notas.getMateria().getIdMateria(),
                notas.getEstudiante().getIdEstudiante(),
                notas.getCalificacion()
        );
    }

    @Override
    @Transactional
    public void actualizarNota(Notas notas) {
        notasDao.actualizarNotas(
                notas.getIdNota(),
                notas.getProfesor().getIdProfesor(),
                notas.getMateria().getIdMateria(),
                notas.getEstudiante().getIdEstudiante(),
                notas.getCalificacion()
        );
    }

    @Override
    @Transactional
    public void delete(Notas nota) {
        notasDao.eliminarNotas(nota.getIdNota());
    }
}
