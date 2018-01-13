export class Shops {
  private id: string;
  private picture: string;
  private name: string;
  private email: string;
  private city: string;
  private location: number[];


  constructor()/*id: string, picture: string, name: string, email: string, city: string, location: number[]) {
    this.id = id;
    this.picture = picture;
    this.name = name;
    this.email = email;
    this.city = city;
    this.location = location;*/{
  }


  getId(): string {
    return this.id;
  }

  setId(value: string) {
    this.id = value;
  }

  getPicture(): string {
    return this.picture;
  }

  setPicture(value: string) {
    this.picture = value;
  }

  getName(): string {
    return this.name;
  }

  setName(value: string) {
    this.name = value;
  }

  getEmail(): string {
    return this.email;
  }

  setEmail(value: string) {
    this.email = value;
  }

  getCity(): string {
    return this.city;
  }

  setCity(value: string) {
    this.city = value;
  }

  getLocation(): number[] {
    return this.location;
  }

  setLocation(value: number[]) {
    this.location = value;
  }



}
