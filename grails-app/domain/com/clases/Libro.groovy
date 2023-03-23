package com.clases

class Libro {

    boolean activo = true

    static belongsTo = [autor: Autor]

    String titulo

    static constraints = {

    }

    static mapping = {
        id generator: 'sequence', params: [sequence_name: 'Libro_seq']
    }
}
