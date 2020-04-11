import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-certificate',
  templateUrl: './certificate.component.html',
  styleUrls: ['./certificate.component.css']
})
export class CertificateComponent implements OnInit {

  model:CertificateViewModel={
    startDate:'',
    endDate:'',
    serialNumber:'',
    name:'',
    surname:'',
    country:'',
    organization:'',
    organizationUnit:'',
    email:'',
    type:'',
  }

  constructor(private http:HttpClient) { }

  ngOnInit(): void {
  }

  generateCertificate():void {
    let url = "http://localhost:9090/api/generate";
    this.http.post(url, this.model).subscribe(
      res=> {
        location.reload();
        alert("Uspe")
      },
      error => {
        alert("Error");
      }
    );
  }
}
export interface CertificateViewModel {
  startDate:string;
  endDate:string;
  serialNumber:string;
  name:string;
  surname:string;
  organization:string;
  organizationUnit:string;
  email:string;
  country:string;
  type:string;
}
