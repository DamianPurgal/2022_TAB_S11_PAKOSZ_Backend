package com.example.skiSlope.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="ticket_options")
public class TicketOption extends Price {

    @Column(name="entries")
    protected int entries;

}
