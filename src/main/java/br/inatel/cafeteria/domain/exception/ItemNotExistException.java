package br.inatel.cafeteria.domain.exception;

public class ItemNotExistException extends RuntimeException{
    public ItemNotExistException(String message) {
        super(message);
    }
}
