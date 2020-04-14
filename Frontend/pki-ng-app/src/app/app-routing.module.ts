import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CertificateComponent} from "./certificate/certificate.component";
import {AppComponent} from "./app.component";
import {CertificateListComponent} from "./certificate-list/certificate-list.component";
import {SelfsignedCertificateComponent} from "./selfsignedCertificate/selfsigned-certificate.component";

const routes: Routes = [
  {
    path:'createCertificate',
    component:CertificateComponent
  },
  {
    path:'certificateList',
    component:CertificateListComponent
  },
  {
    path: 'selfSigned/generate',
    component:SelfsignedCertificateComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
