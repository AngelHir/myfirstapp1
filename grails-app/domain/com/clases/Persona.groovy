package com.clases

class Persona {

    boolean activo=true

    String nombre

    Integer edad


    static constraints = {
        nombre nullable: false, blank: false
        edad nullable: false, blank: false


    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'Persona_seq']
    }
}
