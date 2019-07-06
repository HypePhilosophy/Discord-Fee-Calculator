import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import javax.security.auth.login.LoginException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import net.dv8tion.jda.core.EmbedBuilder;
import java.awt.Color;
import java.time.Instant;

public class feesCalculator  extends ListenerAdapter {

    public static void main(String[] args) throws LoginException {


        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = "Token";
        builder.setToken(token);
        builder.addEventListener(new feesCalculator());
        builder.buildAsync();


    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        System.out.println("Public message received from server " + event.getGuild().getName() +
                ":" + event.getMessage().getContentDisplay());


    event.getJDA().getPresence().setGame(Game.playing("@!Help#0001"));


        String userQuery = (event.getMessage().getContentRaw());
        //userQuery receives the message
        userQuery = userQuery.replace("!calculate ", "");

        String inputAmount = userQuery.replace(",", "");

        double calculatingPercentage = (Double.parseDouble(inputAmount) * 0.029) + 0.3;
        double finalValue = Double.parseDouble(inputAmount) - calculatingPercentage;
        //Paypal Fees
        double grailedFees = (Double.parseDouble(inputAmount) * 0.089) + 0.3;
        double finalGrailed = (Double.parseDouble(inputAmount) - grailedFees);
        //Grailed Fees
        double stockXFees = (Double.parseDouble(inputAmount) * 0.125);
        double finalStockXFees = (Double.parseDouble(inputAmount) - stockXFees);
        //StockX Level 1 Fees
        double stockXFees2 = (Double.parseDouble(inputAmount) * 0.12);
        double finalStockX2 = (Double.parseDouble(inputAmount) - stockXFees2);
        //StockX Level 2 Fees
        double stockXFees3 = (Double.parseDouble(inputAmount) * 0.115);
        double finalStockX3 = (Double.parseDouble(inputAmount) - stockXFees3);
        //StockX Level 3 Fees
        double stockXFees4 = (Double.parseDouble(inputAmount) * 0.11);
        double finalStockX4 = (Double.parseDouble(inputAmount) - stockXFees4);
        //StockX Level 4 Fees
        double goatFees = (Double.parseDouble(inputAmount) * 0.095) + 5;
        double finalGOATFees = (Double.parseDouble(inputAmount) - goatFees);
        //GOAT Fees
        double poshFees = (Double.parseDouble(inputAmount) * 0.2);
        double finalPosh = (Double.parseDouble(inputAmount) - poshFees);
        //Poshmark Fees
        double mercariFees = (Double.parseDouble(inputAmount) * 0.1);
        double finalMercari = (Double.parseDouble(inputAmount) - mercariFees);
        //Mercari Fees
        double dePopFees = (Double.parseDouble(inputAmount) * 0.129);
        double finaldePop = (Double.parseDouble(inputAmount) - dePopFees);
        //DePop Fees


//---------------------------------------------------------------------------------------------------

        if (event.getMessage().getContentRaw().equals("!calculate " + userQuery)) {
            System.out.println("Public message received from user: " + event.getAuthor().getName() +
                    ":" + event.getMessage().getContentDisplay());



            DecimalFormat df = new DecimalFormat("#,###.##");
            df.setRoundingMode(RoundingMode.CEILING);


//---------------------------------------------------------------------------------------------------
            double calculatingEbay = (Double.parseDouble(inputAmount) * 0.118);
            double finalEbay = (Double.parseDouble(inputAmount) - calculatingEbay - 0.3);
            EmbedBuilder eb = new EmbedBuilder();
            eb.setAuthor("Fees Calculator", null, "https://raw.githubusercontent.com/HypePhilosophy/BotImages/master/Screen%20Shot%202018-12-26%20at%204.14.08%20PM.png");
            eb.setTitle("Fees Calculator", null);
            eb.setFooter("Made by !Help#0001", "https://raw.githubusercontent.com/HypePhilosophy/BotImages/master/Screen%20Shot%202018-12-26%20at%204.14.08%20PM.png");
            eb.setTimestamp(Instant.now());
            eb.setColor(Color.red);
            eb.setColor(new Color(0xF40C0C));
            eb.setColor(new Color(255, 0, 54));
            eb.addField("PayPal", "$" + df.format(finalValue), false);
            eb.setDescription("Earnings for $" +df.format( Double.parseDouble(inputAmount)));
            eb.addField("Grailed", "$" + df.format(finalGrailed), false);
                if (calculatingEbay < 50) {
                    eb.addField("eBay", "$" + (df.format(finalEbay)), false);

                }else{
            double overExceed = Double.parseDouble(inputAmount) - 50;
            eb.addField("eBay", "$" + df.format(overExceed), false);

            }
                eb.addField("StockX Regular", "$" + df.format(finalStockXFees), false);
                eb.addField("StockX Lvl 2", "$" + df.format(finalStockX2), true);
                eb.addField("StockX Lvl 3", "$" + df.format(finalStockX3), true);
                eb.addField("StockX Lvl 4", "$" + df.format(finalStockX4), true);
                eb.addField("GOAT", "$" + df.format(finalGOATFees), false);
                eb.addField("Mercari", "$" + df.format(finalMercari), false);
                eb.addField("Depop", "$" + df.format(finaldePop), false);
                eb.addField("Poshmark", "$" + df.format(finalPosh), false);

            event.getChannel().sendMessage(eb.build()).queue();

        }
    }
}
