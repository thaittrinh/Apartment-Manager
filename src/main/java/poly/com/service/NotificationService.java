package poly.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import poly.com.entity.Notification;
import poly.com.repository.NotificationRepository;

@Service
public class NotificationService {
	@Autowired
	NotificationRepository notificationRepository;
	
	
	// ---------------------------------------------------------

    // < --------------------------- find All -------------------------->
    public ResponseEntity<List<Notification>> findAll() {
        List<Notification> notifications = notificationRepository.findAll();
        return ResponseEntity.ok(notifications);
    }

    // < -------------------------- find by Id ---------------------------->
    public ResponseEntity<Notification> findById(int id) {
        try {
        	Notification notification = notificationRepository.findById(id).orElse(null);
            return ResponseEntity.ok(notification);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // < --------------------------- Create ---------------------------------->
 
    public ResponseEntity<Notification> createNotification(Notification notification) {
        try {
            notification.setId(0);
            Notification notification3 = notificationRepository.save(notification);
            return ResponseEntity.ok(notification3);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // < ------------------------------ Update --------------------------------->
  
    public ResponseEntity<Notification> updateNotification(Integer id,Notification notification) {
        try {
        	Notification notification2 = notificationRepository.findById(id).orElse(null);
        	if (notification2 == null)
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            notification.setId(id);
            Notification notification3 = notificationRepository.save(notification);
            return ResponseEntity.ok(notification3);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // < ------------------------------- Delete ----------------------------------->
    public ResponseEntity<String> deleteNotification(int id) {
        try {
            if (!notificationRepository.existsById(id))
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
            notificationRepository.deleteById(id);
            return ResponseEntity.ok("delete success");
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
