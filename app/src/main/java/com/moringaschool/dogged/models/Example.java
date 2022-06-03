
package com.moringaschool.dogged.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Example {

    @SerializedName("message")
    @Expose
    private ListAllBreedsResponse message;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Example() {
    }

    /**
     * 
     * @param message
     * @param status
     */
    public Example(ListAllBreedsResponse message, String status) {
        super();
        this.message = message;
        this.status = status;
    }

    public ListAllBreedsResponse getMessage() {
        return message;
    }

    public void setMessage(ListAllBreedsResponse message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
