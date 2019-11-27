import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {from, Subscription} from "rxjs";
import {UserService} from "../../../../services/user.service";
import {getSourceFile} from "tslint";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html"
})
export class LoginComponent implements OnInit {

  base64ImageTextString: String;
  signUpForm: FormGroup;
  private subscriptions: Subscription[] = [];

  constructor(private formBuilder: FormBuilder, private userService: UserService) {

  }

  ngOnInit() {
    this.signUpForm = this.formBuilder.group({
      "name": ['Den', [Validators.required, Validators.pattern("(^[A-Z]{1}[a-z]{1,20}$)|(^[А-Я]{1}[а-я]{1,20}$)")]],
      "surname": ['Bor', [Validators.required, Validators.pattern("(^[A-Z]{1}[a-z]{1,20}$)|(^[А-Я]{1}[а-я]{1,20}$)")]],
      "phone": ['+375291234567', [Validators.required, Validators.pattern("(^(?!\\+.*\\(.*\\).*\\-\\-.*$)(?!\\+.*\\(.*\\).*\\-$)(\\+[0-9]{1,7}([-0-9]{0,8})?([0-9]{0,1})?)$)|(^[0-9]{1,4}$)")]],
      "username": ['Axaaxax', [Validators.required, Validators.pattern("^[A-Za-z]{1}[0-9A-Za-z]{1,15}$")]],
      "email": ['borden@gmail.com', [Validators.required, Validators.pattern("^(?!.*@.*@.*$)(?!.*@.*\\-\\-.*\\..*$)(?!.*@.*\\-\\..*$)(?!.*@.*\\-$)(.*@.+(\\..{1,11})?)$")]],
      "password": ['12345qW', [Validators.required, Validators.pattern("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}$")]],
      "confirmpassword": ['12345qW', [Validators.required, this.passwordValidator("password")]],
      "role": this.formBuilder.group({
        "id": [10],
        "title": ["admin"]
      }),
      "timeRegistration": [new Date()],
      "mainPhoto": ['']
    })
  }

  public onSubmit(user): void {
    this.saveUser(user);
    this.signUpForm.reset();
  }

  saveUser(user) {
    user.mainPhoto = this.base64ImageTextString;
    this.subscriptions.push(this.userService.saveUser(user).subscribe(res => {
      console.log(res);
    }))
  }

  passwordValidator(otherControlName: string) {

    let thisControl: FormControl;
    let otherControl: FormControl;

    return function matchOtherValidate(control: FormControl) {
      if (!control.parent) {
        return null;
      }

      if (!thisControl) {
        thisControl = control;
        otherControl = control.parent.get(otherControlName) as FormControl;
        if (!otherControl) {
          throw new Error('passwordValidator(): other control is not found in parent group');
        }
        otherControl.valueChanges.subscribe(() => {
          thisControl.updateValueAndValidity();
        });
      }

      if (!otherControl) {
        return null;
      }

      if (otherControl.value !== thisControl.value) {
        return {
          matchOther: true
        };
      }

      return null;

    }

  }

  handleFileSelect(evt) {
    let files = evt.target.files;
    let file = files[0];

    if (files && file) {
      let reader = new FileReader();

      reader.onload = this._handleReaderLoaded.bind(this);

      reader.readAsBinaryString(file);
    }
  }

  _handleReaderLoaded(readerEvt) {
    let binaryString = readerEvt.target.result;
    this.base64ImageTextString = btoa(binaryString);
  }
}
