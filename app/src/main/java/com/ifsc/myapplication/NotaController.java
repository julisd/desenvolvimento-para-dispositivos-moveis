package com.ifsc.myapplication;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class NotaController {

    Context mContext;
     NotaDAO notaDao;


    public NotaController(Context context) {
        mContext=context;
        if(notaDao ==null) {
            notaDao = new NotaDAO(context);
        }
    }

    public Nota cadastrarNota(Nota nota){
        return  notaDao.insertNota(nota);
    }

    public ArrayList<Nota> listaNotas () {
        return notaDao.getListaNotas();
    }

    public ArrayList<String> listaTituloNotas() {
        ArrayList<String> result = new ArrayList<>();
        for (Nota nota: this.listaNotas()
        ) {
            result.add(nota.getTitulo());
        }
        return result;
    }

    public Nota recuperaNota(Integer id){
        return  notaDao.buscaNota(id);
    }
    public boolean atualizaNota(Nota nota){
        return  notaDao.atualizaNota(nota);
    }
    public boolean excluirNota(Nota nota ) {
        return notaDao.removeNota(nota);
    }

}
