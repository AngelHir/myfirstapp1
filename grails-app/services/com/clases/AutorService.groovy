package com.clases

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import org.hibernate.sql.JoinType

@Transactional
class AutorService {

    PersonaService personaService

    LibroService libroService

    Autor get(long id) {
        return Autor.get(id)
    }

    def show(long id) {
        println params
        try {
            Autor autorInstance = autorService.get(id)
            if (autorInstance) {
                render autorInstance as JSON
            } else {
                throw new Exception("No existe con el id " + id)
            }
        } catch (Exception e) {
            Map error = [error: e.getMessage()]
            render error as JSON
        }
    }

    def search(Map autorMap){
        println "autorMap:${autorMap}"
        return Autor.createCriteria().list(offset: 0, max: 10) {
            createAlias('persona', 'per', JoinType.INNER_JOIN)

            eq ("id", (autorMap.id   ?:0) as long)

            if (autorMap.nombre) {
                ilike('per.nombre',"%${autorMap.nombre}%")
            }

            order ("id", "asc")
        }
    }

    def save(Autor autorInstance) throws Exception {
        if (autorInstance && autorInstance.validate()) {
            autorInstance.save(flush: true)
            return autorInstance
        }
        throw new Exception("Errores :${autorInstance.errors}")
    }


    def create(Map autorMap) {
        try {
            Autor autorInstance
            autorInstance = new Autor()
            autorInstance.pesona=personaService.create(autorMap)
            autorInstance.editorial = autorMap.editorial
            autorInstance.ranking = autorMap.ranking as short
            this.save(autorInstance)
        } catch (e) {
            throw new Exception("Errores :${e.getMessage()}")
        }

    }


    def update(Map autorMap){
        try {
            Autor autorInstance
            autorInstance = this.get(autorMap.id as long)
            autorInstance.editorial = autorMap.editorial
            autorInstance.ranking = autorMap.ranking as short
            autorMap.libros.each {Map libroMap ->autorInstance.addToLibros(libroService.create(libroMap,autorInstance))}
            this.save(autorInstance)
            } catch(e) {
            throw new Exception("Errores :${e.getMessage()}")
        }

    }

    def delete(long id){
        try {
            Autor autorInstance = this.get(id)
            if (autorInstance) {
                autorInstance.activo= false
                this.save(autorInstance)
                return autorInstance.id
            } else {
                throw new Exception("No existe con el id " + id)
            }
        } catch(e) {
            throw new Exception("Errores :${e.getMessage()}")
        }
    }

    }



