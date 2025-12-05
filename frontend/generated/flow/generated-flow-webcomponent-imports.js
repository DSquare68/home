import { injectGlobalWebcomponentCss } from 'Frontend/generated/jar-resources/theme-util.js';

import '@vaadin/polymer-legacy-adapter/style-modules.js';
import '@vaadin/vertical-layout/theme/lumo/vaadin-vertical-layout.js';
import '@vaadin/login/theme/lumo/vaadin-login-form.js';
import '@vaadin/button/theme/lumo/vaadin-button.js';
import '@vaadin/tooltip/theme/lumo/vaadin-tooltip.js';
import 'Frontend/generated/jar-resources/disableOnClickFunctions.js';
import '@vaadin/common-frontend/ConnectionIndicator.js';
import '@vaadin/vaadin-lumo-styles/sizing.js';
import '@vaadin/vaadin-lumo-styles/spacing.js';
import '@vaadin/vaadin-lumo-styles/style.js';
import '@vaadin/vaadin-lumo-styles/vaadin-iconset.js';
import 'Frontend/generated/jar-resources/ReactRouterOutletElement.tsx';

const loadOnDemand = (key) => {
  const pending = [];
  if (key === '2437be73e8a02730fc4c59e6044f603307c7c7ddd9cc28634e7cf6bf763036ff') {
    pending.push(import('./chunks/chunk-cda41d330d0ec92e37e806bd24f14f4eb90b1bcc91da2c28dab38db1a7b6f7e3.js'));
  }
  if (key === '926a2ce3ad76562c857b8756e628d71235d87adf7701bcd2a983f8b98e350ffe') {
    pending.push(import('./chunks/chunk-3e9538517eb0482fed53021e3e08662a9409f7eaf1cfa7803037e21c7d15af1b.js'));
  }
  if (key === 'b32a35cedc912d0d7e6cc7c92e4164352621e07dad0ff00c1a2102716f5fd7d9') {
    pending.push(import('./chunks/chunk-e6d7f827972099d1e59b0a79d9f08c81c88f89a0e2dab6ca0a7be73b04ff9898.js'));
  }
  if (key === '311e7bd1d115291162309c80abc92f8ee60a4ae1f42a10813f187b4fe6eb3c9d') {
    pending.push(import('./chunks/chunk-e6d7f827972099d1e59b0a79d9f08c81c88f89a0e2dab6ca0a7be73b04ff9898.js'));
  }
  if (key === 'aaa39b51c3c09c492d1e3723055c470fe7752b1363e8ad58aeedb3b6b3b268d8') {
    pending.push(import('./chunks/chunk-f0bad609f6ecd19d2a7978a23d9b0bac6b12273bd8b243a7bd5da922828ac200.js'));
  }
  return Promise.all(pending);
}

window.Vaadin = window.Vaadin || {};
window.Vaadin.Flow = window.Vaadin.Flow || {};
window.Vaadin.Flow.loadOnDemand = loadOnDemand;
window.Vaadin.Flow.resetFocus = () => {
 let ae=document.activeElement;
 while(ae&&ae.shadowRoot) ae = ae.shadowRoot.activeElement;
 return !ae || ae.blur() || ae.focus() || true;
}