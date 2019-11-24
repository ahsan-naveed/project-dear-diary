from settings import Settings
from ship import Ship

import sys
import pygame

class AlienInvasion:
    """ Overall class to manage game assets and behavior. """
    def __init__(self):
        """ Initialize the game, and create game resources. """
        pygame.init()
        self.settings = Settings()

        print(self.settings.screen_width)

        self.screen = pygame.display.set_mode((
            self.settings.screen_width,
            self.settings.screen_height))
        pygame.display.set_caption("Alien Invasion")

        self.ship = Ship(self)

        # set the background color.
        self.bg_color = (230, 230, 230)

    def run_game(self):
        """ Start the main loop for the game. """
        while True:
            # watch for keyboard and mouse events.
            self._check_events()
            
            # Redraw the screen during each pass through the loop.
            self._update_screen()
    
    def _check_events(self):
        """Respond to keypresses and mouse events."""
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                sys.exit()
            elif event.type == pygame.KEYDOWN:
                if event.type == pygame.K_RIGHT:
                    print('moving baby')
                    self.ship.rect.x += 1
                elif event.type == pygame.K_LEFT:
                    self.ship.rect.x -= 1
                elif event.type == pygame.K_UP:
                    self.ship.rect.y += 1
                elif event.type == pygame.K_DOWN:
                    self.ship.rect.y -= 1
    
    def _update_screen(self):
        """Update images on the screen, and flip to the new screen."""
        self.screen.fill(self.settings.bg_color)
        self.ship.blitme()
                
        # Make the most recently drawn screen visible.
        pygame.display.flip()

if __name__ == "__main__":
    # Make  a game instance and run the game.
    ai = AlienInvasion()
    ai.run_game()