import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CertificateServiceService} from "../servis/certificate-service.service";
import {Certificate} from "../model/certificate";
import {CertificateDB} from "../model/certificateDB";

@Component({
  selector: 'app-certificate',
  templateUrl: './certificate.component.html',
  styleUrls: ['./certificate.component.css']
})
export class CertificateComponent implements OnInit {

  listOfPossibleIssuers: CertificateDB []
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
    alias:'',
  }

  constructor(private http:HttpClient,private certService: CertificateServiceService) { }

  ngOnInit(): void {
    this.certService.getAllPossibleIssuers().subscribe(
      data=> {
        this.listOfPossibleIssuers=data;
      },
      error => {
        console.log('Error occured', error);
      }
    );
  }

  generateCertificate():void {
    let url = "http://localhost:9090/api/generate";
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
  alias:string;
  type:string;
  alias:string;
}
