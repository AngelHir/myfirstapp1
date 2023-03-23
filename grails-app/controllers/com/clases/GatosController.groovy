package com.clases

import grails.converters.JSON


class GatosController {

    GatosService gatosService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        render Gatos.list([order: 'asc', sort: "id"]) as JSON
    }

    def show(long id) {
        println params
        try {
            Gatos gatosInstance = gatosService.get(id)
            if (gatosInstance) {
                render gatosInstance as JSON
            } else {
                throw new Exception("No existe con el id " + id)
            }
        } catch (Exception e) {
            Map error = [error: e.getMessage()]
            render error as JSON
        }
    }

    def saveOrUpdate(long id) {
        println params
        try {
        render gatosService.createOrUpdate(JSON.parse(request) as Map) as JSON
            Map result = [success: true]
            respond result

        } catch (Exception e) {
            Map error = [error: e.getMessage()]
            render error as JSON
        }

    }



    def delete(long id) {
        println params
        try {

            Map result = [success: gatosService.delete(id)]
            respond result
        } catch (Exception e) {
            Map error = [error: e.getMessage()]
            render error as JSON
        }

    }


 }

