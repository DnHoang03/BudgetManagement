import { Component, signal } from '@angular/core';
import { Login } from '../../model/login';
import { AuthService } from '../../service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  login:Login = {}
  constructor(private authService:AuthService, private router:Router){}
  userLogin() {
    this.authService.login(this.login).subscribe(authResponse => {
      localStorage.setItem('token', authResponse.token);
      console.log(authResponse.token)
      this.router.navigate(['/home'])
    })
  }
  hide = true;
  clickEvent(event: MouseEvent) {
    this.hide = (!this.hide);
    event.stopPropagation();
  }
}
