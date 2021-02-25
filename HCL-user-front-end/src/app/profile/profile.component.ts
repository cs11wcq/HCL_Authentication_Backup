import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../_services/token-storage.service';

/*

gets current User from Storage using TokenStorageService and show information (username, token, email, roles).
*/

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  currentUser: any;

  constructor(private token: TokenStorageService) { }

  ngOnInit(): void {
    this.currentUser = this.token.getUser();
  }

}
