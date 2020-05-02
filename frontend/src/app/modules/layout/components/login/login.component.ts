import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {AuthToken, UserService} from "../../../../services/user.service";
import {LoginModel} from "../../../../models/login.model";
import {User} from "../../../../models/user";
import {StorageService} from "../../../../services/storage.service";
import {Router} from "@angular/router";
import {Role} from "../../../../models/role";
import {RoleService} from "../../../../services/role.service";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html"
})
export class LoginComponent implements OnInit {

  public loginModel: LoginModel = {};
  base64ImageTextString: String;
  signUpForm: FormGroup;
  public showCheckYourSetDataAlert: boolean = false;
  checkUsername: boolean = false;
  roleUser: Role;
  private subscriptions: Subscription[] = [];

  constructor(private formBuilder: FormBuilder, private userService: UserService,
              private storageService: StorageService, private router: Router,
              private roleService: RoleService) {

  }

  ngOnInit() {
    this.signUpForm = this.formBuilder.group({
      "name": ['', [Validators.required, Validators.pattern("(^[A-Z]{1}[a-z]{1,20}$)|(^[А-Я]{1}[а-я]{1,20}$)")]],
      "surname": ['', [Validators.required, Validators.pattern("(^[A-Z]{1}[a-z]{1,20}$)|(^[А-Я]{1}[а-я]{1,20}$)")]],
      "phone": ['', [Validators.required, Validators.pattern("(^(?!\\+.*\\(.*\\).*\\-\\-.*$)(?!\\+.*\\(.*\\).*\\-$)(\\+[0-9]{1,7}([-0-9]{0,8})?([0-9]{0,1})?)$)|(^[0-9]{1,4}$)")]],
      "username": ['', [Validators.required, Validators.pattern("^[A-Za-z]{1}[0-9A-Za-z]{1,15}$")]],
      "email": ['', [Validators.required, Validators.pattern("^(?!.*@.*@.*$)(?!.*@.*\\-\\-.*\\..*$)(?!.*@.*\\-\\..*$)(?!.*@.*\\-$)(.*@.+(\\..{1,11})?)$")]],
      "password": ['', [Validators.required, Validators.pattern("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,}$")]],
      "confirmpassword": ['', [Validators.required, this.passwordValidator("password")]],
      "role": this.formBuilder.group({
        "id": '',
        "title": ''
      }),
      "timeRegistration": [new Date()],
      "mainPhoto": ['']
    })
  }

  public onSignUp(user): void {
    this.roleService.getRoleByTitle("ADMIN").subscribe(role => {
      user.role.id = role.id;
      user.role.title = role.title;
      this.saveUser(user);
    });
  }

  public onSignIn(loginModel: LoginModel): void {
    this.userService.generateToken(loginModel)
      .subscribe((authToken: AuthToken) => {
        if (authToken.token) {
          this.storageService.setToken(authToken.token);
          this.userService.getAuthorizedUser()
            .subscribe((user: User) => {
              this.storageService.setCurrentUser(user);
              this.router.navigate(['/home']);
            });
        }
      }, (error) => {
        if (error.status === 401) {
          this.showCheckYourSetDataAlert = true;
        } else {
          alert(error.message);
        }
      });

  }

  saveUser(user) {
    user.mainPhoto = this.base64ImageTextString;
    this.subscriptions.push(this.userService.saveUser(user).subscribe(() => {
      let val: LoginModel = {username: user.username, password: user.password};
      this.onSignIn(val)
    }, err => {
      this.checkUsername = true;
    }));
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
