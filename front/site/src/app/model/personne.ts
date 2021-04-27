export class Personne {
  private _first_name: string;
  private _last_name: string;
  private _email: string;
  private _password: string;


  constructor() {
    this._first_name ='';
    this._last_name ='';
    this._email ='';
    this._password='';
  }

  get first_name(): string {
    return this._first_name;
  }

  set first_name(value: string) {
    this._first_name = value;
  }

  get last_name(): string {
    return this._last_name;
  }

  set last_name(value: string) {
    this._last_name = value;
  }

  get email(): string {
    return this._email;
  }

  set email(value: string) {
    this._email = value;
  }

  get password(): string {
    return this._password;
  }

  set password(value: string) {
    this._password = value;
  }
}
