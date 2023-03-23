package com.clases

class Autor {

    static hasMany = [libros: Libro]

    Persona pesona

    short ranking = (0 as short)

    boolean activo = true

    String editorial

    static constraints = {
        ranking min: (0 as short), max: (5 as short)
        editorial nullable: false, blank: false

    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'Autor_seq']
    }
}
