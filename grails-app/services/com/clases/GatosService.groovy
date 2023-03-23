package com.clases

import grails.gorm.transactions.Transactional
import groovy.sql.Sql
import netscape.javascript.JSObject

import javax.sql.DataSource

@Transactional
class GatosService {
    DataSource dataSource

    Gatos get(long id){
        return Gatos.get(id)
    }

    def list() {
        Sql sqlInstance = new Sql(dataSource)
        //3 consultas
        List gatos = []
        //Dinamic querys
        Gatos.findAllByNombreAndActivo('pablo', true).each { gato ->
            if (gato.raza.equals('persia')) {
                gatos.add(gato)
            }
        }
        gatos = gatos.sort { it.nombre }

        gatos = Gatos.findAllByNombre('pablo').collect {[
                id: it.id,
                descripcion: "${it.nombre} es de raza ${it.raza}"
        ]}.sort {it.descripcion }

        // Criteeria
        Gatos.createCriteria().list(offset: 0, max: 10) {
            eq ("nombre", "pablo")
            eq("activo", true)
            order (nombre, "asc")
        }

        //Data source
        sqlInstance.rows("SELECT * FROM gatos WHERE nombre ilike '%${'pablo'}%'")
        return gatos
    }

    def save(Gatos gatosInstance) throws Exception {
        if(gatosInstance && gatosInstance.validate()) {
            gatosInstance.save(flush:true)
            return gatosInstance
        }
        throw new Exception("Errores :${gatosInstance.errors}")
    }

    def createOrUpdate(Map gatosMap) {
        try {
            Gatos gatosInstance
            if (gatosMap.id) {
                gatosInstance = this.get(gatosMap.id)
            }else{
                gatosInstance=new Gatos()
            }
            gatosInstance.nombre=gatosMap.nombre
            gatosInstance.raza=gatosMap.raza
            gatosInstance.peso=gatosMap.peso
            this.save(gatosInstance)
        } catch(e) {
            throw new Exception("Errores :${e.getMessage()}")
        }

    }

 def delete(long id){
     try {
         Gatos gatosInstance = this.get(id)
         if (gatosInstance) {
             gatosInstance.activo= false
          // this.save(gatosInstance)
             return gatosInstance.id
         } else {
             throw new Exception("No existe con el id " + id)
         }
     } catch(e) {
         throw new Exception("Errores :${e.getMessage()}")
     }
 }


}
