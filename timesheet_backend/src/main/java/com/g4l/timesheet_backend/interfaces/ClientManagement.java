package com.g4l.timesheet_backend.interfaces;

import java.util.List;

import com.g4l.timesheet_backend.models.entities.client.Client;

public interface ClientManagement {
    public Client createClient(Client client);
    public Client getClient(String clientId);
    public List<Client> getAllClients();
    public Client updateClient(Client client);
    public Client deleteClient(String clientId);
}
