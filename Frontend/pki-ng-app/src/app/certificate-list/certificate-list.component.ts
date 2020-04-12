import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Certificate} from "../model/certificate";
import {CertificateServiceService} from "../servis/certificate-service.service";


@Component({
  selector: 'app-certificate-list',
  templateUrl: './certificate-list.component.html',
  styleUrls: ['./certificate-list.component.css']
})
export class CertificateListComponent implements OnInit {

  title='Sertifikati';
  myResponse: Certificate[];

  id:string;

  constructor(private certService: CertificateServiceService) { }

  ngOnInit(): void {
    this.certService.getAllCertificates().subscribe(
      data=> {
        this.myResponse=data;
      },
      error => {
        console.log('Error occured', error);
      }
    );
  }
  revoke(certificate:Certificate){
    this.certService.revokeCertificate(certificate).subscribe(
      res=> {
        location.reload();
      },
      error => {
        alert("Error");
      }
    );
  }


}
