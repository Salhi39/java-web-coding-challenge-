import {Component, OnInit} from '@angular/core';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';
import {AuthService} from '../../server/auth/auth.service';
import {Observable} from 'rxjs/Observable';
import {delay} from 'q';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  myResult: Observable<any>;
  form: FormGroup;
  private formSubmitAttempt: boolean;
  status: string;

  constructor(private fb: FormBuilder,
              private authService: AuthService) {
  }

  ngOnInit() {
    this.status = '';
    this.form = this.fb.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  isFieldInvalid(field: string) {
    return (
      (!this.form.get(field).valid && this.form.get(field).touched) ||
      (this.form.get(field).untouched && this.formSubmitAttempt)
    );
  }

  onSubmit() {
    if (this.form.valid) {
      this.myResult = this.authService.login(this.form.value, status);
      if (this.myResult != null) {
        this.myResult.subscribe(value => {
          this.status = value['status'];
          this.status = value['status'];
          setTimeout(() => {
            this.status = '';
          }, 3000);
        });
      }
    }
    this.formSubmitAttempt = true;
  }

}


