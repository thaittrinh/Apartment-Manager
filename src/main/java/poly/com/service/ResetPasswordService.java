package poly.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import poly.com.entity.Employee;
import poly.com.entity.PasswordResetToken;
import poly.com.repository.EmployeeRepository;
import poly.com.repository.PasswordResetRespository;

@Service
public class ResetPasswordService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PasswordResetRespository passwordResetRespository;


    /* ----------------------------- Reset Password ----------------------*/
    public ModelAndView sendtokentoemail(ModelAndView modelAndView, String email) {
        /*  check exist email */
        Employee employee = employeeRepository.findByEmail(email).orElse(null);

        if (employee != null) {
            /*save it */
            PasswordResetToken passwordResetToken = new PasswordResetToken(employee);
            passwordResetRespository.save(passwordResetToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email);
            mailMessage.setSubject("Xác nhận đặt lại mật khẩu  ");
            mailMessage.setFrom("ndt.programmer@gmail.com");
            mailMessage.setText(
                    "Xin chào Bạn "
                            + "chúng tôi đã nhận được yêu cầu  đặt lại mật khẩu của bạn "
                            + "Vui lòng click vào link bên dưới để đặt lại mật khẩu "
                            + "http://localhost:8081/apartment-manage.com.vn/api/account/confirm-reset?token="
                            + passwordResetToken.getToken());
            emailSenderService.sendEmail(mailMessage);
            modelAndView.setViewName("/contents/resetpassword/form-check-email");
            modelAndView.addObject("messageSuccess",
                    "Kiểm tra email của bạn để đặt lại mật khẩu");
        } else {/* if email dose not exist return not found */
            modelAndView.addObject("messageError", "Email này không tồn tại, vui lòng kiểm tra lại ");
            modelAndView.setViewName("/contents/resetpassword/form-check-email");
        }
        return modelAndView;
    }

    /*  ------------------------------------- validateresettoken -----------------------  */
    public ModelAndView validateresettoken(ModelAndView modelAndView, String token) {
        PasswordResetToken passwordResetToken = passwordResetRespository.findByToken(token);

        if (passwordResetToken != null) {
            Employee employee = employeeRepository.findByEmail(passwordResetToken.getEmployee().getEmail()).orElse(null);
            employeeRepository.save(employee);
            modelAndView.addObject("employee", employee);
            modelAndView.addObject("email", employee.getEmail());
            modelAndView.setViewName("/contents/resetpassword/form-reset-password");
/*            passwordResetRespository.deleteByToken(token);*/
        } else {
            modelAndView.addObject("message", "Liên kết không hợp lệ hoặc bị hỏng!");
            modelAndView.setViewName("/error");
        }
        return modelAndView;
    }

    /* ------------------------------------resetpassword ---------------------------*/
    public ModelAndView resetpassword(ModelAndView modelAndView, Employee employee) {
        if (employee.getEmail() != null) {
            Employee tokenEmployee = employeeRepository.findByEmail(employee.getEmail()).orElse(null);
            tokenEmployee.setPassword(passwordEncoder.encode(employee.getPassword()));
            employeeRepository.save(tokenEmployee);
            modelAndView.addObject("messageSuccess", "Password successfully reset. You can now log in with the new credentials.");
            modelAndView.setViewName("/contents/resetpassword/form-reset-password");
        } else {
            modelAndView.addObject("message", "The link is invalid or broken!");
            modelAndView.setViewName("/error");
        }
        return modelAndView;
    }
}
