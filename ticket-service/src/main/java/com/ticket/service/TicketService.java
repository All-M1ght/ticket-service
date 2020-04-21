package com.ticket.service;

import com.ticket.model.Ticket;

public interface TicketService {
    Ticket bookTicket(Long tid);
    boolean sellTicket(Long tid);
}
