import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginDTO } from 'src/app/dtos/user/login.dto';
import { Role } from 'src/app/models/role';
import { LoginResponse } from 'src/app/responses/user/login.response';
import { RoleService } from 'src/app/services/role.service';
import { TokenService } from 'src/app/services/token.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  @ViewChild('loginForm') loginForm!: NgForm;

  phoneNumber: string = '';
  password: string = '';
  roles: Role[] = [];
  rememberMe: boolean = true;
  selectedRole: Role | undefined;

  onPhoneNumberChange() {
    console.log(`Phone typed: ${this.phoneNumber}`);
    //how to validate ? phone must be at least 6 characters
  }
  constructor(
    private router: Router,
    private userService: UserService,
    private tokenService: TokenService,
    private roleService: RoleService
  ) {}

  ngOnInit() {
    this.roleService.getRoles().subscribe({
      next: (roles: Role[]) => {
        this.roles = roles;
        this.selectedRole = roles.length > 0 ? roles[0] : undefined;
      },
      error: (error: any) => {
        console.error('Error getting roles:', error);
      },
    });
  }

  login() {
    const message = `phone: ${this.phoneNumber}` + `password: ${this.password}`;

    const loginDTO: LoginDTO = {
      phone_number: this.phoneNumber,
      password: this.password,
      role_id: this.selectedRole?.id ?? 1,
    };
    this.userService.login(loginDTO).subscribe({
      next: (response: LoginResponse) => {
        const { token } = response;
        if (this.rememberMe) {
          this.tokenService.setToken(token);
        }
        //this.router.navigate(['/login']);
      },
      complete: () => {},
      error: (error: any) => {
        alert(error.error.message);
      },
    });
  }
}
