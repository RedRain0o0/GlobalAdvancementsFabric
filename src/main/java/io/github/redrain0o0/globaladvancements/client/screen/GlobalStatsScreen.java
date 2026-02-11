package io.github.redrain0o0.globaladvancements.client.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.tabs.LoadingTab;
import net.minecraft.client.gui.components.tabs.Tab;
import net.minecraft.client.gui.components.tabs.TabManager;
import net.minecraft.client.gui.components.tabs.TabNavigationBar;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class GlobalStatsScreen extends Screen {
    private static final Component TITLE = Component.translatable("gui.stats");
    static final ResourceLocation SLOT_SPRITE = ResourceLocation.withDefaultNamespace("container/slot");
    static final ResourceLocation HEADER_SPRITE = ResourceLocation.withDefaultNamespace("statistics/header");
    static final ResourceLocation SORT_UP_SPRITE = ResourceLocation.withDefaultNamespace("statistics/sort_up");
    static final ResourceLocation SORT_DOWN_SPRITE = ResourceLocation.withDefaultNamespace("statistics/sort_down");
    private static final Component PENDING_TEXT = Component.translatable("multiplayer.downloadingStats");
    static final Component NO_VALUE_DISPLAY = Component.translatable("stats.none");
    private static final Component GENERAL_BUTTON = Component.translatable("stat.generalButton");
    private static final Component ITEMS_BUTTON = Component.translatable("stat.itemsButton");
    private static final Component MOBS_BUTTON = Component.translatable("stat.mobsButton");
    protected final Screen lastScreen;
    private static final int LIST_WIDTH = 280;
    final HeaderAndFooterLayout layout = new HeaderAndFooterLayout(this);
    private final TabManager tabManager = new TabManager((guiEventListener) -> {
        AbstractWidget var10000 = (AbstractWidget)this.addRenderableWidget(guiEventListener);
    }, (guiEventListener) -> this.removeWidget(guiEventListener));
    @Nullable private TabNavigationBar tabNavigationBar;
    private boolean isLoading = true;

    public GlobalStatsScreen(@Nullable Screen screen) {
        super(TITLE);
        this.lastScreen = screen;
    }

    protected void init() {
        Component component = PENDING_TEXT;
        this.tabNavigationBar = TabNavigationBar.builder(this.tabManager, this.width).addTabs(new Tab[]{new LoadingTab(this.getFont(), GENERAL_BUTTON, component), new LoadingTab(this.getFont(), ITEMS_BUTTON, component), new LoadingTab(this.getFont(), MOBS_BUTTON, component)}).build();
        this.addRenderableWidget(this.tabNavigationBar);
        this.layout.addToFooter(Button.builder(CommonComponents.GUI_DONE, (button) -> this.onClose()).width(200).build());
        this.tabNavigationBar.setTabActiveState(0, true);
        this.tabNavigationBar.setTabActiveState(1, false);
        this.tabNavigationBar.setTabActiveState(2, false);
        this.layout.visitWidgets((abstractWidget) -> {
            abstractWidget.setTabOrderGroup(1);
            this.addRenderableWidget(abstractWidget);
        });
        this.tabNavigationBar.selectTab(0, false);
        this.repositionElements();
        //this.minecraft.getConnection().send(new ServerboundClientCommandPacket(ServerboundClientCommandPacket.Action.REQUEST_STATS));
    }

    protected void repositionElements() {
        if (this.tabNavigationBar != null) {
            this.tabNavigationBar.setWidth(this.width);
            this.tabNavigationBar.arrangeElements();
            int i = this.tabNavigationBar.getRectangle().bottom();
            ScreenRectangle screenRectangle = new ScreenRectangle(0, i, this.width, this.height - this.layout.getFooterHeight() - i);
            this.tabNavigationBar.getTabs().forEach((tab) -> tab.visitChildren((abstractWidget) -> abstractWidget.setHeight(screenRectangle.height())));
            this.tabManager.setTabArea(screenRectangle);
            this.layout.setHeaderHeight(i);
            this.layout.arrangeElements();
        }
    }

    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        super.render(guiGraphics, i, j, f);
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, Screen.FOOTER_SEPARATOR, 0, this.height - this.layout.getFooterHeight(), 0.0F, 0.0F, this.width, 2, 32, 2);
    }

    protected void renderMenuBackground(GuiGraphics guiGraphics) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, CreateWorldScreen.TAB_HEADER_BACKGROUND, 0, 0, 0.0F, 0.0F, this.width, this.layout.getHeaderHeight(), 16, 16);
        this.renderMenuBackground(guiGraphics, 0, this.layout.getHeaderHeight(), this.width, this.height);
    }

    public void onClose() {
        this.minecraft.setScreen(this.lastScreen);
    }
}
