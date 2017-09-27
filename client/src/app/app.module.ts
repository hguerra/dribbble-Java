import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HeaderComponent} from './header/header.component';
import {FooterComponent} from './footer/footer.component';
import {MenuComponent} from './menu/menu.component';
import {ScreenshotsComponent} from './screenshots/screenshots.component';
import {PaginationComponent} from './pagination/pagination.component';
import {ScreenshotComponent} from './screenshots/screenshot/screenshot.component';
import {ScreenshotService} from './screenshots/screenshot.service';
import {ROUTES} from './app.routes';
import {RouterModule} from '@angular/router';
import {HttpModule} from '@angular/http';
import { FavoritesComponent } from './screenshots/favorites/favorites.component';
import { FavoritesRecentComponent } from './screenshots/favorites-recent/favorites-recent.component';
import { FavoritesDateComponent } from './screenshots/favorites-date/favorites-date.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    MenuComponent,
    ScreenshotsComponent,
    PaginationComponent,
    ScreenshotComponent,
    FavoritesComponent,
    FavoritesRecentComponent,
    FavoritesDateComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    RouterModule.forRoot(ROUTES)
  ],
  providers: [ScreenshotService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
