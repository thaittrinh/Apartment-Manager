package poly.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import poly.com.entity.TokenResetPasswrod;

import java.util.Date;

public interface PasswordResetRespository extends JpaRepository<TokenResetPasswrod, Integer> {
    TokenResetPasswrod findByToken(String token);

    @Modifying
    @Query("delete from TokenResetPasswrod t where t.expiryDate <= ?1")
    void deleteAllByExpiryDate(Date now);

}