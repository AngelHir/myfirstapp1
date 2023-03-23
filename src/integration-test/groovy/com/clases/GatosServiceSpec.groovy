package com.clases

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class GatosServiceSpec extends Specification {

    GatosService gatosService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Gatos(...).save(flush: true, failOnError: true)
        //new Gatos(...).save(flush: true, failOnError: true)
        //Gatos gatos = new Gatos(...).save(flush: true, failOnError: true)
        //new Gatos(...).save(flush: true, failOnError: true)
        //new Gatos(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //gatos.id
    }

    void "test get"() {
        setupData()

        expect:
        gatosService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Gatos> gatosList = gatosService.list(max: 2, offset: 2)

        then:
        gatosList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        gatosService.count() == 5
    }

    void "test delete"() {
        Long gatosId = setupData()

        expect:
        gatosService.count() == 5

        when:
        gatosService.delete(gatosId)
        sessionFactory.currentSession.flush()

        then:
        gatosService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Gatos gatos = new Gatos()
        gatosService.save(gatos)

        then:
        gatos.id != null
    }
}
