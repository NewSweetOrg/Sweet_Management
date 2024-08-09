package services;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MessagingService {
    private Map<String, LinkedList<String>> storeOwnerMessages = new HashMap<>();
    private Map<String, LinkedList<String>> supplierMessages = new HashMap<>();

    // Sends a message to a store owner and stores the body in a LinkedList
    public void sendMessageToStoreOwner(String userId, String storeOwnerId, String messageBody) {
        storeOwnerMessages.computeIfAbsent(storeOwnerId, k -> new LinkedList<>()).add(messageBody);
    }

    // Sends a message to a supplier and stores the body in a LinkedList
    public void sendMessageToSupplier(String userId, String supplierId, String messageBody) {
        supplierMessages.computeIfAbsent(supplierId, k -> new LinkedList<>()).add(messageBody);
    }

    // Retrieves the messages for a specific store owner
    public LinkedList<String> getMessagesForStoreOwner(String storeOwnerId) {
        return storeOwnerMessages.getOrDefault(storeOwnerId, new LinkedList<>());
    }

    // Retrieves the messages for a specific supplier
    public LinkedList<String> getMessagesForSupplier(String supplierId) {
        return supplierMessages.getOrDefault(supplierId, new LinkedList<>());
    }
}