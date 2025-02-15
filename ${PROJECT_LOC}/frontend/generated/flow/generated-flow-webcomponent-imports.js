import { injectGlobalWebcomponentCss } from 'Frontend/generated/jar-resources/theme-util.js';

import '@vaadin/tooltip/theme/lumo/vaadin-tooltip.js';
import '@vaadin/polymer-legacy-adapter/style-modules.js';
import '@vaadin/button/theme/lumo/vaadin-button.js';
import 'Frontend/generated/jar-resources/buttonFunctions.js';
import '@vaadin/vertical-layout/theme/lumo/vaadin-vertical-layout.js';
import '@vaadin/common-frontend/ConnectionIndicator.js';
import '@vaadin/vaadin-lumo-styles/sizing.js';
import '@vaadin/vaadin-lumo-styles/spacing.js';
import '@vaadin/vaadin-lumo-styles/style.js';
import '@vaadin/vaadin-lumo-styles/vaadin-iconset.js';
import 'Frontend/generated/jar-resources/ReactRouterOutletElement.tsx';

const loadOnDemand = (key) => {
  const pending = [];
  if (key === '4bcd5fdf74608ac9dbb0c4ca09bce56d58be4f68105d711389f672f0cb946ffc') {
    pending.push(import('./chunks/chunk-5bd30f8b09a2281006e43d00b76c60d97c5f854c6e5117bd24ee69170924d579.js'));
  }
  if (key === '562a2a7148376dc3de6124f765b185431677f3892e68eb9601152324f23ab1f6') {
    pending.push(import('./chunks/chunk-b7d663b84a7d9d0c4a34c2cdb0b725e85e95aacf1abb8867e9fe1602f0c6d7bb.js'));
  }
  if (key === '926a2ce3ad76562c857b8756e628d71235d87adf7701bcd2a983f8b98e350ffe') {
    pending.push(import('./chunks/chunk-f439767ed069a01e2347f982437780df6dc1410e17174e9e5c01bdbafd659480.js'));
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