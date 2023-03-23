package com.clases

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PerrosServiceSpec extends Specification {

    PerrosService perrosService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Perros(...).save(flush: true, failOnError: true)
        //new Perros(...).save(flush: true, failOnError: true)
        //Perros perros = new Perros(...).save(flush: true, failOnError: true)
        //new Perros(...).save(flush: true, failOnError: true)
        //new Perros(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //perros.id
    }

    void "test get"() {
        setupData()

        expect:
        perrosService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Perros> perrosList = perrosService.list(max: 2, offset: 2)

        then:
        perrosList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        perrosService.count() == 5
    }

    void "test delete"() {
        Long perrosId = setupData()

        expect:
        perrosService.count() == 5

        when:
        perrosService.delete(perrosId)
        sessionFactory.currentSession.flush()

        then:
        perrosService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Perros perros = new Perros()
        perrosService.save(perros)

        then:
        perros.id != null
    }
}
