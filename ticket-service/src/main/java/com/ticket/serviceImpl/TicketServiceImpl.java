package com.ticket.serviceImpl;

import com.ticket.dao.TicketRepository;
import com.ticket.model.Ticket;
import com.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Iterator;

@Service
public class TicketServiceImpl implements TicketService {
    @Resource
    private TicketRepository ticketRepository;
    @Override
    public Ticket bookTicket(Long tid) {
        Ticket ticket = null;
        try {
            ticket = ticketRepository.findById(tid).get();
        }catch (Exception e){
            System.out.println("ticket not exist");
        }
        if(ticket != null && ticket.getState() == 0){
            ticket.setState(1);
            ticket = ticketRepository.save(ticket);
        }else {
            ticket = null;
        }
        return ticket;
    }

    @Override
    public boolean sellTicket(Long tid) {
        Ticket ticket = null;
        try {
            ticket = ticketRepository.findById(tid).get();
        }catch (Exception e){
            System.out.println("not exist");
        }
        if(ticket != null){
            ticket.setState(2);
            ticketRepository.save(ticket);
            return true;
        }
        return false;
    }
}
