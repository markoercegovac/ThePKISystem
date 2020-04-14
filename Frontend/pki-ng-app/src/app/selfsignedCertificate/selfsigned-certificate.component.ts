import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Certificate} from "../model/certificate";

import {CertificateServiceService} from "../servis/certificate-service.service";

@Component({
  selector: 'app-selfsigned-certificate',
  templateUrl: './selfsigned-certificate.component.html',
  styleUrls: ['./selfsigned-certificate.component.css']
})
export class SelfsignedCertificateComponent implements OnInit {


  model:SelfsignedCertificateViewModel={
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
    alias:'',
  }

  constructor(private http:HttpClient ) { }

  ngOnInit(): void {


  }


  generateSelfSignedCertificate() {
    let url = "http://localhost:9090/api/generate/selfSigned/generate";
    this.http.post(url, this.model).subscribe(
      res=> {
        location.reload();
        alert("Uspesno");
      },
      error => {
        alert("Error");
      }
    );
  }


}
export interface SelfsignedCertificateViewModel {
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
  alias:string;
}
