package com.clases

import grails.gorm.transactions.Transactional

@Transactional
class LibroService {


    Libro get(long id) {
        return Libro.get(id)
    }

    def index(){

    }

    def save(Libro libroInstance) throws Exception {
        if (libroInstance && libroInstance.validate()) {
            libroInstance.save(flush: true)
            return libroInstance
        }
        throw new Exception("Errores :${libroInstance.errors}")
    }


    def create(Map libroMap, Autor autorInstance) {
        try {
            Libro libroInstance
            libroInstance = new Libro()
            libroInstance.titulo = libroMap.titulo
            libroInstance.autor = autorInstance
            return this.save(libroInstance)
        } catch (e) {
            throw new Exception("Errores :${e.getMessage()}")
        }

    }


    def update(Map libroMap){
        try {
            Libro libroInstance
            libroInstance = this.get(libroMap.id as long)
            libroInstance.titulo = libroMap.titulo
            this.save(libroInstance)
        } catch(e) {
            throw new Exception("Errores :${e.getMessage()}")
        }

    }

    def delete(long id){
        try {
            Libro libroInstance= this.get(id)
            if (libroInstance) {
                libroInstance.activo= false
                this.save(libroInstance)
                return libroInstance.id
            } else {
                throw new Exception("No existe con el id " + id)
            }
        } catch(e) {
            throw new Exception("Errores :${e.getMessage()}")
        }
    }
}