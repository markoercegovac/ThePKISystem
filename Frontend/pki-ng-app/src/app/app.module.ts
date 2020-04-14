import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationComponent } from './navigation/navigation.component';
import { CertificateComponent } from './certificate/certificate.component';
import { CertificateListComponent } from './certificate-list/certificate-list.component';
import { FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { SelfsignedCertificateComponent } from './selfsignedCertificate/selfsigned-certificate.component';


@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    CertificateComponent,
    CertificateListComponent,
    SelfsignedCertificateComponent,

  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule, //registrovanje servisa ne moramo explicitno da navedemo, ovo radi za nas
    AppRoutingModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
