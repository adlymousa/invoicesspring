package com.harri.invoicesspring.exceptions;

public class InvoiceNotFoundException extends Exception {

    private String title;

    public static InvoiceNotFoundException createWith(String title) {
        return new InvoiceNotFoundException(title);
    }

    private InvoiceNotFoundException(String title) {
        this.title = title;
    }

    @Override
    public String getMessage() {
        return "Invoice '" + title + "' not found";
    }

}
