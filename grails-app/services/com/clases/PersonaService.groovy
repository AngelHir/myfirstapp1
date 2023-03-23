package com.clases

import grails.gorm.transactions.Transactional

@Transactional
class PersonaService {


    Persona get(long id) {
        return Persona.get(id)
    }

    def index(){

    }

    def save(Persona personaInstance) throws Exception {
        if (personaInstance && personaInstance.validate()) {
            personaInstance.save(flush: true)
            return personaInstance
        }
        throw new Exception("Errores :${personaInstance.errors}")
    }


    def create(Map personaMap) {
        try {
            Persona personaInstance
            personaInstance = new Persona()
            personaInstance.nombre = personaMap.nombre
            personaInstance.edad = personaMap.edad as Integer
            return this.save(personaInstance)
        } catch (e) {
            throw new Exception("Errores :${e.getMessage()}")
        }

    }


    def update(Map personaMap){
        try {
            Persona personaInstance
            personaInstance = this.get(personaMap.id as long)
            personaInstance.nombre = personaMap.nombre
            personaInstance.edad = personaMap.edad as Integer
            this.save(personaInstance)
        } catch(e) {
            throw new Exception("Errores :${e.getMessage()}")
        }

    }

    def delete(long id){
        try {
            Persona personaInstance = this.get(id)
            if (personaInstance) {
                personaInstance.activo= false
                this.save(personaInstance)
                return personaInstance.id
            } else {
                throw new Exception("No existe con el id " + id)
            }
        } catch(e) {
            throw new Exception("Errores :${e.getMessage()}")
        }
    }
}

