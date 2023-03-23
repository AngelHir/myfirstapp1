package com.clases

class Gatos {

    boolean activo=true

    String nombre

    String raza

    float peso



    static constraints = {
        nombre nullable: false, blank: false, unique: true
        raza nullable: true, blank: false
        peso nullable: false, blanck: false
    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'Gatos_seq']
    }
}
