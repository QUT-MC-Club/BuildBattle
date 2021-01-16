package eu.pb4.buildbattle.game.map;

import eu.pb4.buildbattle.game.BBActive;
import eu.pb4.buildbattle.game.BBPlayer;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import xyz.nucleoid.plasmid.util.BlockBounds;
import xyz.nucleoid.plasmid.util.PlayerRef;

import java.util.ArrayList;
import java.util.List;

public class BuildArena {
    public final BlockBounds area;
    public final BlockBounds ground;
    public final BlockBounds bounds;
    public final BlockBounds spawn;
    public final List<BBPlayer> players = new ArrayList();
    public int score = 0;

    public BuildArena(BlockBounds area, BlockBounds ground, BlockBounds bounds, BlockBounds spawn) {
        this.area = area;
        this.ground = ground;
        this.bounds = bounds;
        this.spawn = spawn;
    }

    public boolean canBuild(BlockPos blockPos, BBPlayer bbPlayer) {
        return this.players.contains(bbPlayer) && this.area.contains(blockPos);
    }

    public Text getBuilders(BBActive game) {
        if (this.players.isEmpty()) {
            return new LiteralText("Nobody");
        } else {
            LiteralText text = new LiteralText("");

            for (BBPlayer bbPlayer : this.players) {
                int index = this.players.indexOf(bbPlayer);
                if (index != 0) {
                    if (this.players.size() - index == 1) {
                        text.append(new TranslatableText("buildbattle.text.and").formatted(Formatting.GOLD));
                    } else {
                        text.append(new LiteralText(", ").formatted(Formatting.GOLD));
                    }
                }

                if (game.participants.get(bbPlayer.playerRef) != null) {
                    text.append(game.gameSpace.getPlayers().getEntity(bbPlayer.playerRef.getId()).getDisplayName());
                } else {
                    text.append(new TranslatableText("buildbattle.text.disconnected").formatted(Formatting.GRAY));
                }
            }
            return text;
        }
    }

}